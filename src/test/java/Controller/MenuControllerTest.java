package Controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import com.rps.rpsgame.controller.MenuController;
import com.rps.rpsgame.model.OptionsModel;
import com.rps.rpsgame.model.SummaryRound;
import com.rps.rpsgame.model.SummaryRounds;
import com.rps.rpsgame.service.GameServiceImpl;

public class MenuControllerTest {

    private static final String EXPECTED_VIEW = "/index";

    @InjectMocks
    private MenuController menuController;

    @MockBean
    private GameServiceImpl gameService;

    private Model model;
    private SummaryRounds summaryRounds;

    @BeforeEach
    public void doSomething() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(new GameServiceImpl()).build();
    }

    @Before
    public void setUp(){
//        model = new Model();
        summaryRounds = new SummaryRounds();
    }

    @Test
    public void play_OK(){

        Mockito.doReturn(fillSummaryRounds(this.summaryRounds)).when(this.gameService).playRound(this.summaryRounds);

        SummaryRounds result = gameService.playRound(this.summaryRounds);

        assert result.getRounds() != null;
        assert result.getRounds().size() == 2;
        Mockito.verify(this.gameService,Mockito.times(1)).playRound(this.summaryRounds);
    }

    @Test
    public void reset_OK(){

        String result = menuController.reset(this.summaryRounds, model);

        assert EXPECTED_VIEW.equals(result);

        Mockito.verify(this.menuController,Mockito.times(1)).reset(this.summaryRounds,model);
    }

    private SummaryRounds fillSummaryRounds (SummaryRounds totalRounds){

        SummaryRound round1 = SummaryRound.builder().player1(OptionsModel.randomOption()).player2(OptionsModel.randomOption()).name("Player1").build();
        SummaryRound round2 = SummaryRound.builder().player1(OptionsModel.randomOption()).player2(OptionsModel.randomOption()).name("Player2").build();
        List<SummaryRound> rounds = new ArrayList<>();
        rounds.add(round1);
        rounds.add(round2);
        totalRounds.setRounds(rounds);

        return totalRounds;
    }

}
