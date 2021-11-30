package pontosecurity.dao.impl;

import org.springframework.stereotype.Repository;

import pontosecurity.bean.Funcionario;
import pontosecurity.commons.AbstractHibernateDao;
import pontosecurity.dao.IFuncionarioDao;

@Repository
public class FuncionarioDao extends AbstractHibernateDao<Funcionario> implements IFuncionarioDao {

    public FuncionarioDao() {
        super(Funcionario.class);
    }
}
