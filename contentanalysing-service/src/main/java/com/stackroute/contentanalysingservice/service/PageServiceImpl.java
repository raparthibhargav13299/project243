package com.stackroute.contentanalysingservice.service;

import com.stackroute.contentanalysingservice.model.ExternalApiResponse;
import com.stackroute.contentanalysingservice.model.Response;
import com.stackroute.contentanalysingservice.repository.TermsRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PageServiceImpl implements PageService {
    TermsRepository termsRepository;
    int intentCount = 0;

    int img = 0, vid = 0;

    RabbitTemplate rabbitTemplate;

    @Value("${spring.rabbitmq.exchange}")
    private String exchange;

    @Value("${spring.rabbitmq.routingkey2}")
    private String routingkey;


//    @RabbitListener(queues = "${spring.rabbitmq.queue}")
//    public void listen(ExternalApiResponse externalApiResponse) {
//        System.out.println(externalApiResponse);
//        getTheData(externalApiResponse);
//    }


    @Autowired
    public PageServiceImpl(TermsRepository termsRepository, RabbitTemplate rabbitTemplate) {
        this.termsRepository = termsRepository;
        this.rabbitTemplate = rabbitTemplate;
    }

    //making temporary intent changes


    @Override
    public List<Response> getTheData(ExternalApiResponse externalApiResponse) {

        int count = 0;
        List<Response> list = new ArrayList<>();
        Response response;


        if (externalApiResponse.getType().equals("page")) {
            Map<String, Integer> intent = new HashMap<>();
            // Added all the keywords with which we will be able to identify the category of the page

            List<String> begin = termsRepository.getAllKeywordsBasedOnCategory("beginner"); // initializing the array as a list
            List<String> medium = termsRepository.getAllKeywordsBasedOnCategory("intermediate");
            List<String> last = termsRepository.getAllKeywordsBasedOnCategory("expert");

            try {//connecting to the particular website using jsoup
                Document doc = Jsoup.connect(externalApiResponse.getUrls().get(0).getLink()).get();
                Elements images = doc.getElementsByTag("img");
                for (Element tag : images) { // finding the number of images present in the page
                    img++;
                }
                Elements meta = doc.select("meta[property=og:video]"); // css query for extracting if any videos is present in the page
                for (Element video : meta
                ) {
                    vid++; //incrementing the value if any video is present
                }


                String[] data = doc.text().split("[^0-9a-zA-Z:,]+");
                for (String s : data
                ) {
                    count++;
                }


                intent.put("beginner", 0);
                intent.put("expert", 0);
                intent.put("intermediate", 0);
                if ((int) (count / 8) < 125) {
                    count = 1;
                }
                count = (int) (count / 8);

                for (String word : data) {
                    if (begin.contains(word) || medium.contains(word) || last.contains(word)) { //checking if the particular word is present in any of them
                        String node = termsRepository.getCategoryNameBasedOnKeyword(word);// check the parent of the node
//                    increment the value with 1 if the word belongs to the particular parent
                        intent.put(node, intent.get(node) + 1);
                    }
                }
//
//                String intentValue = intentResult(intentCount);//temporary changes
//                if (intentCount < 10) {
//                    intentCount++;
//                } else {
//                    intentCount = 0;
//                }
                response = new Response(externalApiResponse.getType(), externalApiResponse.getUrls().get(0).getTitle(), externalApiResponse.getDomain(), externalApiResponse.getConcept(), externalApiResponse.getUrls().get(0).getLink(), "BEGINNER", confidenceScore(intent.get("beginner"), intent.get("intermediate"), intent.get("expert")), Integer.toString(count), vid, img);

                vid = 0;
                img = 0;


                if (Integer.parseInt(response.getDuration()) != 0) {
                    rabbitTemplate.convertAndSend(exchange, routingkey, response);
                }
                intent.clear();
                list.add(response);
                System.out.println(response);

            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        return list;
    }


    //temporary function for intent

    public String intentResult(int counter) {
        if (counter > 2 && counter < 5) {
            return "INTERMEDIATE";
        } else if (counter==6) {
            return "EXPERT";
        }
        return "BEGINNER";
    }

    public String max(int num1, int num2, int num3) {
        try {
            if (num1 >= num2 && num1 >= num3) {
                return "beginner";
            } else if (num2 >= num1 && num2 >= num3)
                return "Intermediate";
        } catch (Exception exception) {
            return "intermediate";
        }
        return "Expert";
    }

    private int confidenceScore(int first, int second, int third) {
        int max = maxValue(first, second, third);
        int compute = 0;
        try {
            compute = (int) (10.0 * (max / (first + second + third)));
        } catch (Exception exception) {
            compute = 4;
        }

        return compute;
    }

    public int maxValue(int num1, int num2, int num3) {
        if (num1 >= num2 && num1 >= num3)
            return num1;

        else if (num2 >= num1 && num2 >= num3)
            return num2;

        return num3;
    }


}
