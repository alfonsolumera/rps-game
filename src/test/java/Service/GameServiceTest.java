package Service;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;

import com.rps.rpsgame.model.GameSummary;
import com.rps.rpsgame.model.OptionsModel;
import com.rps.rpsgame.model.SummaryRound;
import com.rps.rpsgame.service.GameService;
import com.rps.rpsgame.service.GameServiceImpl;
import com.rps.rpsgame.utils.Constants;

@SpringBootApplication
@SpringBootTest
public class GameServiceTest {

    @InjectMocks
    private GameServiceImpl gameService;

    private GameService spy;

    @BeforeEach
    public void setUp(){
        spy = Mockito.spy(this.gameService);
    }

    @Test
    public void playRound() {

        List<SummaryRound> result = spy.playRound(fillSummaryRounds());

        assert result != null;
        assert !result.isEmpty();
        assert OptionsModel.ROCK.equals(result.get(0).getPlayer2());
    }

    @Test
    public void playRound1() {

        List<SummaryRound> result = spy.playRound(null);

        assert result != null;
        assert !result.isEmpty();
        assert OptionsModel.ROCK.equals(result.get(0).getPlayer2());
    }

    @Test
    public void playRound2() {

        List<SummaryRound> result = spy.playRound(fillSummaryRounds());

        assert result != null;
        assert !result.isEmpty();
        assert OptionsModel.ROCK.equals(result.get(0).getPlayer2());
    }

    @Test
    public void summary() {

        GameSummary result = spy.getSummary();

        assert result != null;

    }

    private List<SummaryRound> fillSummaryRounds (){

        List<SummaryRound> rounds = new ArrayList<>();

        SummaryRound round1 = SummaryRound.builder().player1(OptionsModel.PAPER).player2(OptionsModel.ROCK).name(Constants.PLAYER_ONE).build();
        SummaryRound round2 = SummaryRound.builder().player1(OptionsModel.ROCK).player2(OptionsModel.ROCK).name(Constants.TIE).build();
        SummaryRound round3 = SummaryRound.builder().player1(OptionsModel.SCISSOR).player2(OptionsModel.ROCK).name(Constants.PLAYER_TWO).build();

        rounds.add(round1);
        rounds.add(round2);
        rounds.add(round3);

        return rounds;
    }

}
