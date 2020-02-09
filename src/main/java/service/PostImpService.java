package service;

import dao.PostDao;
import entity.Locality;
import entity.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import view.PostView;

import java.util.ArrayList;
import java.util.List;

@Service
@Repository("PostService")
public class PostImpService implements PostService {

    @Qualifier("PostDao")
    @Autowired
    private PostDao postDao;

    @Override
    public List<PostView> getAllPostView() {
        List<Post> listPost=postDao.getAll();
        List<PostView> postViewList=new ArrayList<PostView>();

        for(Post x : listPost){
            postViewList.add(new PostView(x.getIdPost(),x.getNamePost(),x.getDistanceBeetwenSensors(),
                    x.getGeographkoordsId().getKoordinate(),x.getGeographkoordsId().getHeighSeaLevel(),x.getBasinlocalityId().getDescription(),
                    x.getGroundId().getTypeGround(),x.getLocalityId().getNameLocality(),x.getDescription()));
        }
        return postViewList;
    }

    @Override
    public PostView searсhPostViev(int idPost) {
        Post post=postDao.searсh(idPost);
        PostView postView=new PostView(post.getIdPost(),post.getNamePost(),post.getDistanceBeetwenSensors(),
                post.getGeographkoordsId().getKoordinate(),post.getGeographkoordsId().getHeighSeaLevel(),post.getBasinlocalityId().getDescription(),
                post.getGroundId().getTypeGround(),post.getLocalityId().getNameLocality(),post.getDescription());
        return postView;
    }

    @Override
    public List<PostView> searhPostViev(String namePost) {
        return null;
    }

    @Override
    public List<PostView> searhPostViev(Locality locality) {
        return null;
    }

//    @Override
//    public List<PostView> searhPostViev(Geometry koords) {
//        return null;
//    }
}
