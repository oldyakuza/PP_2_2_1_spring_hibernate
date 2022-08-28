package hiber.service;

import hiber.dao.CarDao;
import hiber.model.Car;
import hiber.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CarServiceImp implements CarService {
    private final CarDao carDao;

    CarServiceImp(CarDao carDao) {
        this.carDao = carDao;
    }
    @Transactional
    @Override
    public void add(Car car) {
        carDao.add(car);
    }

    @Override
    public User getOwner(String model, int series) {
        return carDao.getOwner(model, series);
    }
}
