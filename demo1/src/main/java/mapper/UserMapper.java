//package mapper;
//
//// 在 Mapper 接口上使用 @CacheNamespace 注解
//@CacheNamespace
//public interface UserMapper {
//    main.java.entity.User selectById(Long id);
//}
//// 使用二级缓存的示例
//SqlSession session = sqlSessionFactory.openSession();
//
//try {
//// 查询用户，结果将被存储在二级缓存中
//main.java.entity.User user1 = session.getMapper(UserMapper.class).selectById(1L);
//
//// 关闭当前会话，然后重新打开一个新的会话
//    session.close();
//session = sqlSessionFactory.openSession();
//
//// 在新的会话中再次查询相同的用户，这次将从二级缓存中获取结果
//main.java.entity.User user2 = session.getMapper(UserMapper.class).selectById(1L);
//
//// 检验缓存是否失效：在二级缓存中，可以通过比较 user1 和 user2 是否相同来检验
//    if (user1 == user2) {
//        System.out.println("二级缓存有效");
//    } else {
//            System.out.println("二级缓存失效");
//    }
//
//            } finally {
//            session.close(); // 关闭 SqlSession
//}