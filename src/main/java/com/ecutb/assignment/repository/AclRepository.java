package com.ecutb.assignment.repository;

import com.ecutb.assignment.entity.Acl;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AclRepository extends CrudRepository<Acl, String> {
}
