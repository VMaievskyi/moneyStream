package com.piramida.dao.email.impl;

import org.springframework.stereotype.Repository;

import com.piramida.dao.AbstractGenegicDao;
import com.piramida.dao.email.EmailDao;
import com.piramida.entity.Email;

@Repository("emailDao")
public class EmailDaoImpl extends AbstractGenegicDao<Email> implements EmailDao {

    @Override
    protected String getEntityName() {
	return "Email";
    }

}
