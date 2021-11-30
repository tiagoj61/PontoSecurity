package pontosecurity.bo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pontosecurity.bean.Funcionario;
import pontosecurity.bo.IFuncionarioBo;
import pontosecurity.commons.AbstractService;
import pontosecurity.commons.IOperations;
import pontosecurity.dao.IFuncionarioDao;

@Service
public class FuncionarioBo extends AbstractService<Funcionario> implements IFuncionarioBo {

    @Autowired
    private IFuncionarioDao dao;

    @Override
    protected IOperations<Funcionario> getDao() {
        return dao;
    }


}
