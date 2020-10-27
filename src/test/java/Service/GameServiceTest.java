package Service;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.rps.rpsgame.model.OptionsModel;
import com.rps.rpsgame.model.SummaryRound;
import com.rps.rpsgame.model.SummaryRounds;
import com.rps.rpsgame.service.GameService;
import com.rps.rpsgame.service.GameServiceImpl;

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

        SummaryRounds result = spy.playRound(fillSummaryRounds(new SummaryRounds()));

        assert result != null;
        assert !result.getRounds().isEmpty();
        assert OptionsModel.ROCK.equals(result.getRounds().get(0).getPlayer2());

    }

    private SummaryRounds fillSummaryRounds (SummaryRounds totalRounds){

        SummaryRound round1 = SummaryRound.builder().player1(OptionsModel.randomOption()).player2(OptionsModel.ROCK).name("Player1").build();
        SummaryRound round2 = SummaryRound.builder().player1(OptionsModel.randomOption()).player2(OptionsModel.ROCK).name("Player2").build();
        List<SummaryRound> rounds = new ArrayList<>();
        rounds.add(round1);
        rounds.add(round2);
        totalRounds.setRounds(rounds);

        return totalRounds;
    }

}
