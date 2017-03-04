package ph.com.rdtech.image;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

/**
 * Created by Daniel on 04/03/2017.
 */
@Repository
@Transactional
public class ImageDao {

    @Autowired
    private SessionFactory _sessionFactory;

    private Session getSession() {
        return _sessionFactory.getCurrentSession();
    }

    public Image getDefaultImage(){
        return (Image) getSession().createQuery("from Image where imageType = 'DEFAULT'").uniqueResult();
    }

    public void update(Image image) {
        getSession().update(image);
        return;
    }
}
