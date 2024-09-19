package JDK_new_function.stream;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import java.util.Base64;
import java.util.function.Function;

import static java.util.stream.Collectors.joining;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/9/19 22:12
 */
public class gracefullyUseStream {

    public static void main(String[] args){
        Stream<List<String>> listStream = Stream.of(Arrays.asList("a", "b"), Arrays.asList("c", "d"));
        Stream<String> stringStream = listStream.flatMap(list -> list.stream());
        stringStream.forEach(System.out::println);
    }

    gracefullyUseStream(){
        Stream.of("i", "am", "xjjdog")
//                .map(toUpperCase())
//                .map(toBase64())
                .collect(joining(" "));

        //除了map和flatMap的函数可以做语义化，更多的filter可以使用Predicate去代替
        //registarIsCorrect，就可以当作filter的参数。
//        Predicate<Registar> registarIsCorrect = reg ->
//                reg.getRegulationId() != null
//                        && reg.getRegulationId() != 0
//                        && reg.getType() == 0;
    }

    public void UseOptional(){
//        String result = Optional.ofNullable(order)
//                .flatMap(order->order.getLogistics())
//                .flatMap(logistics -> logistics.getAddress())
//                .flatMap(address -> address.getCountry())
//                .map(country -> country.getIsocode())
//                .orElse(Isocode.CHINA.getNumber());

        //尽量的少使用Optional的get方法
//        Optional<String> userName = "xjjdog";
//        String defaultEmail = userName
//                .map(e -> e + "@xjjdog.cn")
//                .orElse("");
    }

    public void SplitFunction(){
        //存在构造函数public OrderDto(Order order)
//    public Stream<OrderDto> getOrderByUser(String userId){
//        return orderRepo.findOrderByUser().stream()
//                .map(OrderDto::new);
//    }


//    public Stream<OrderDto> getOrderByUser(String userId){
//    return orderRepo.findOrderByUser().stream()
//        .map(this::toOrderDto);
//    }
//    public OrderDto toOrderDto(Order order){
//    OrderDto dto = new OrderDto();
//            dto.setOrderId(order.getOrderId());
//            dto.setTitle(order.getTitle().split("#")[0]);
//            dto.setCreateDate(order.getCreateDate().getTime());
//    return dto;
//    }
    }

}
