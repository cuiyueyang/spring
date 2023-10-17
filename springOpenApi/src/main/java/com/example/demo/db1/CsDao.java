package com.example.demo.db1;

import com.example.demo.domain.Cs;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * <p>Description: </p>
 * <p>Date: 2023/04/11 14:28</p>
 *
 * @author cuiyy
 * @version v1.0.0
 **/
public interface CsDao extends JpaRepository<Cs, String> {
}
