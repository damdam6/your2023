package com.ssafy.imgMaker22.config;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.ssafy.imgMaker22.model.service.prompt.dto.ImageUrlDto;
import org.springframework.context.annotation.Configuration;

import java.util.List;

import static com.ssafy.imgMaker22.util.JsonUtil.addJsonObject;
import static com.ssafy.imgMaker22.util.JsonUtil.createJson;

@Configuration
public class GPT4Config {
    public static final String AUTHORIZATION = "Authorization";
    public static final String BEARER = "Bearer ";
    public static final String MEDIA_TYPE = "application/json; charset=UTF-8";
    public static final String CHAT_URL = "https://api.openai.com/v1/chat/completions";
    public static final String MODEL = "gpt-4-vision-preview";
    public static final String MAX_TOKENS = "300";

    /**
     *
     *
     * @param text : GPT4 모델에 요청할 프롬프트
     * @param imageUrls : 이미지 url을 포함한 ImageRequest
     * @return : 프롬프트와 이미지 url을 포함한 GPT4 API 요청 Request을 String 형태로 반환
     */
    public static String createRequestParam(String text, List<ImageUrlDto> imageUrls){

        JsonArray content = new JsonArray();
        content.add(createJson("type", "text", "text", text));

        for(ImageUrlDto imageRequest : imageUrls){
            JsonObject imageJson = addJsonObject(createJson("type", "image_url"),
                    "image_url", createJson("url", imageRequest.getUrl()));
            content.add(imageJson);
        }

        JsonObject msg = addJsonObject(createJson("role", "user"),
                "content", content);
        JsonArray messages = addJsonObject(new JsonArray(), msg);

        return addJsonObject(createJson("model", MODEL, "max_tokens", MAX_TOKENS),
                "messages", messages).toString();
    }

    /**
     *
     * @param style : 생성하고 싶은 이미지의 스타일
     * @return : GPT4에 요청하는 프롬프트를 리턴
     */
    public static String createPrompt(String style){
        return "make a prompt for dalli to draw " +  style + " style picture using elements from these pictures.\n" +
                "do not draw picture. just tell me the prompt";
    }
}
