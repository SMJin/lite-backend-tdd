package io.hhplus.tdd.point;

import io.hhplus.tdd.database.PointHistoryTable;
import io.hhplus.tdd.database.UserPointTable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PointControllerTest {

    private PointService pointService;

    @BeforeEach
    public void createService() {
        this.pointService = new PointService(new UserPointTable(), new PointHistoryTable());
    }

    @Test
    void givenIdGetPoint() {
        // given
        long id = 1L;

        // when
        UserPoint result = pointService.get(1L);

        // then
        assertThat(result.id()).isEqualTo(id);
    }

    @Test
    void history() {
    }

    @Test
    void charge() {
        // given
        long id = 1L;
        int amount = 100;

        // when
        UserPoint result = pointService.charge(id, amount);

        // then
        assertThat(result.point()).isEqualTo(100);
    }

    @Test
    void use() {
        // given
        long id = 1L;
        int amount = 50;

        // when
        UserPoint result = pointService.use(id, amount);

        // then
        assertThat(result.point()).isEqualTo(-50);
    }
}