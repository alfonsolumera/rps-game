package Controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;

import com.rps.rpsgame.controller.MenuController;
import com.rps.rpsgame.model.GameSummary;
import com.rps.rpsgame.model.OptionsModel;
import com.rps.rpsgame.model.SummaryRound;
import com.rps.rpsgame.service.GameServiceImpl;

@SpringBootApplication
@SpringBootTest
public class MenuControllerTest {

    private static final String INDEX_VIEW = "index";
    private static final String SCORE_BOARD_VIEW = "summary";
    private static final String ZERO = "0";
    private static final String FOUR = "4";

    @InjectMocks
    private MenuController menuController;

    @Mock
    private GameServiceImpl gameService;

    private Model model;
    private List<SummaryRound> summaryRounds;
    private HttpServletRequest httpSession;

    @BeforeEach
    public void doSomething() {
        model = new ConcurrentModel();
        summaryRounds = fillSummaryRounds();
        httpSession = new MockHttpServletRequest();
    }

    @Test
    public void emptyInit(){

        httpSession.getSession().setAttribute("rounds", null);
        httpSession.getSession().setAttribute("roundsPlayed", null);
        String result = menuController.init(this.httpSession);
        List<SummaryRound> rounds = (List<SummaryRound>) this.httpSession.getSession().getAttribute("rounds");
        String roundPlayed = (String) this.httpSession.getSession().getAttribute("roundsPlayed");

        assert INDEX_VIEW.equals(result);
        assert rounds.isEmpty();
        assert roundPlayed.equals(ZERO);
    }

    @Test
    public void init(){

        httpSession.getSession().setAttribute("rounds", fillSummaryRounds());
        httpSession.getSession().setAttribute("roundsPlayed", "4");
        String result = menuController.init(this.httpSession);
        List<SummaryRound> rounds = (List<SummaryRound>) this.httpSession.getSession().getAttribute("rounds");
        String roundPlayed = (String) this.httpSession.getSession().getAttribute("roundsPlayed");

        assert INDEX_VIEW.equals(result);
        assert !rounds.isEmpty();
        assert roundPlayed.equals(FOUR);
    }

    @Test
    public void playFirstRound(){

        httpSession.setAttribute("rounds", null);
        String view = menuController.play(this.httpSession);

        Mockito.doReturn(this.summaryRounds).when(this.gameService).playRound(this.summaryRounds);

        List<SummaryRound> result = gameService.playRound(this.summaryRounds);

        assert INDEX_VIEW.equals(view);
        assert result != null;
        assert result.size() == 2;
        Mockito.verify(this.gameService,Mockito.times(1)).playRound(this.summaryRounds);
    }

    @Test
    public void playAnotherRound(){

        httpSession.setAttribute("rounds", fillSummaryRounds());
        String view = menuController.play(httpSession);

        Mockito.doReturn(this.summaryRounds).when(this.gameService).playRound(this.summaryRounds);

        List<SummaryRound> result = gameService.playRound(this.summaryRounds);

        assert INDEX_VIEW.equals(view);
        assert result != null;
        assert result.size() == 2;
        Mockito.verify(this.gameService,Mockito.times(1)).playRound(this.summaryRounds);
    }

    @Test
    public void reset(){

        String result = menuController.reset(this.httpSession);
        List<SummaryRound> rounds = (List<SummaryRound>) this.httpSession.getSession().getAttribute("rounds");
        String roundPlayed = (String) this.httpSession.getSession().getAttribute("roundsPlayed");

        assert INDEX_VIEW.equals(result);
        assert rounds.isEmpty();
        assert roundPlayed.equals(ZERO);

    }

    @Test
    public void gameSummary(){

        Mockito.doReturn(GameSummary.builder().totalDraws(0).totalWinsPlayerOne(0).totalWinsPlayerTwo(0).totalRounds(0).build()).when(this.gameService).getSummary();
        String result = menuController.showSummary(this.httpSession);

        assert SCORE_BOARD_VIEW.equals(result);
        Mockito.verify(this.gameService,Mockito.times(1)).getSummary();

    }

    private List<SummaryRound> fillSummaryRounds (){

        List<SummaryRound> rounds = new ArrayList<>();

        SummaryRound round1 = SummaryRound.builder().player1(OptionsModel.randomOption()).player2(OptionsModel.randomOption()).name("Player1").build();
        SummaryRound round2 = SummaryRound.builder().player1(OptionsModel.randomOption()).player2(OptionsModel.randomOption()).name("Player2").build();

        rounds.add(round1);
        rounds.add(round2);

        return rounds;
    }
    
    //Pilar es muuuuu lista

}
