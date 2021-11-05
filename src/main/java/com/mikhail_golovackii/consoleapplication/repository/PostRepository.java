
package com.mikhail_golovackii.consoleapplication.repository;

import com.mikhail_golovackii.consoleapplication.model.Label;
import com.mikhail_golovackii.consoleapplication.model.Post;
import java.util.List;

public interface PostRepository extends GenericRepository<Post, Long>{
    
    Post create(Post elem, Long labelId);
    
    Post create(Post elem, List<Long> labelsId);
    
    Post addLabel(Long postId, Long labelId);
    
    Post addLabels(Long postId, List<Long> labelsId);
        
    Post deleteLabel(Long postId, Long labelId);
    
    Post deleteLabels(Long postId);
}
