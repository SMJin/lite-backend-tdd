package io.hhplus.tdd.point;

import io.hhplus.tdd.database.PointHistoryTable;
import io.hhplus.tdd.database.UserPointTable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PointService {

    private final UserPointTable userPointRepository;
    private final PointHistoryTable pointHistoryRepository;

    public UserPoint get(long id) {
        return userPointRepository.selectById(id);
    }

    public UserPoint charge(long id, long amount) {
        UserPoint userPoint = userPointRepository.selectById(id);
        if (userPoint == null) {
            userPoint = UserPoint.empty(id);
        }

        long updated = userPoint.point() + amount;
        UserPoint updatedUserPoint = new UserPoint(id, updated, System.currentTimeMillis());

        userPointRepository.insertOrUpdate(id, updated);
        return updatedUserPoint;
    }

    public List<PointHistory> history(long id) {
        return pointHistoryRepository.selectAllByUserId(id);
    }

    public UserPoint use(long id, long amount) {
        UserPoint userPoint = userPointRepository.selectById(id);

        long updated = userPoint.point() - amount;
        UserPoint updatedUserPoint = new UserPoint(id, updated, System.currentTimeMillis());

        userPointRepository.insertOrUpdate(id, updated);
        return updatedUserPoint;
    }

}
