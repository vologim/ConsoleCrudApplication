
package com.mikhail_golovackii.consoleapplication.repository;

import com.mikhail_golovackii.consoleapplication.model.Writer;
import java.util.List;

public interface WriterRepository extends GenericRepository<Writer, Long>{
    
    Writer create(Writer elem, Long postId);
    
    Writer create(Writer elem, List<Long> postsId);
    
    Writer addPost(Long writerId, Long postId);
    
    Writer addPosts(Long writerId, List<Long> postsId);
        
    Writer deletePost(Long writerId, Long postId);
    
    Writer deletePosts(Long writerId);
}
