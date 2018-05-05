package br.com.uol.testebackend.infra.config;

import java.io.IOException;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;
import org.springframework.http.HttpHeaders;

/**
 * Cliente pra conexao com url externa para para obtencao do Body 
 */
@Component("clientHttpUrl")
public class ClientHttpUrl {

      public String getBodyByUrl(String url,HttpHeaders header) throws IOException{
             CloseableHttpClient httpClient = HttpClientBuilder.create().build();            
          
            HttpGet request = new HttpGet(url);
            request.addHeader("content-type", header.getContentType().toString());
            HttpResponse result = httpClient.execute(request);
            return EntityUtils.toString(result.getEntity(), "UTF-8");
      }  
}
