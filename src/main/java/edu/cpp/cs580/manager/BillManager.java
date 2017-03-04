package edu.cpp.cs580.manager;

import edu.cpp.cs580.util.Bill;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by hardeepsingh on 2/15/17.
 */
public interface BillManager extends CrudRepository<Bill, Integer> {

    List<Bill> findById(int id);

    List<Bill> findByName(String name);

    List<Bill> findByAmount(String amount);

    List<Bill> findByDuedate(String date);

    List<Bill> findByUserid(int id);
}
