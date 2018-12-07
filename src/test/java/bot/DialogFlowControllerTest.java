package bot;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.google.gson.Gson;

import bot.beans.DialogFlowRequest;
import bot.beans.QueryResult;
import bot.constants.Constants;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class DialogFlowControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private DialogFlowRequest request;
    @Autowired
    private QueryResult query;
    
    private Gson gson = new Gson();
    
    final private static String responseId = "ab254df6-204d-4d08-9e04-0c2c8d63a6d9";
    final private static String sessionId = "projects/test1-79103/agent/sessions/6ace457d-f29f-ca89-4227-b5c174f25af9";

    @Test
    public void noParamDialogFlowShouldReturnDefaultMessage() throws Exception {
        query.setAction(Constants.GET_CLUBS_ACTION);
        request.setSession(sessionId);
        request.setResponseId(responseId);
        request.setQueryResult(query);
        this.mockMvc.perform(post("/sams/chatBot").content(gson.toJson(request))
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk());
    }

}

