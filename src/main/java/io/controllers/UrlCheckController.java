package io.controllers;
import java.io.IOException;
        import java.net.HttpURLConnection;
        import java.net.MalformedURLException;
        import java.net.URL;

        import org.springframework.web.bind.annotation.GetMapping;
        import org.springframework.web.bind.annotation.RequestParam;
        import org.springframework.web.bind.annotation.RestController;

@RestController
public class UrlCheckController {
    private final String SITE_IS_UP="Site is Up!";
    private final String SITE_IS_DOWN="Site is Down!";
    private final String INCORRECT_URL="Url is Incorrect!";

    @GetMapping("/check")
    public String getUrlStatusMessage(@RequestParam String url){
        String ReturnMessage = "";
        try {
            URL urlobj=new URL(url);
            HttpURLConnection conn=(HttpURLConnection) urlobj.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            int responsecodenum=conn.getResponseCode()/100;
            if(responsecodenum!=2||responsecodenum!=3){
                ReturnMessage=SITE_IS_DOWN;
            }
            else {
                ReturnMessage=SITE_IS_UP;
            }
        } catch (MalformedURLException e) {
            ReturnMessage=INCORRECT_URL;

        } catch (IOException e) {
            ReturnMessage=SITE_IS_DOWN;
        }
        return ReturnMessage;
    }

}
