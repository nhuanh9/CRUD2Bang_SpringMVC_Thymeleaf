package repository;

import model.Customer;
import model.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICustomerRepository extends JpaRepository<Customer, Long> {
    // extends 1 interface nào đó của Spring Data JPA sẽ làm cho interface này
    // có đầy đủ các phương thức crud liên quan đến class Customer
    // mọi người cần chuyền vào class và kiểu dữ liệu của khoá chính của class này
    Iterable<Customer> findAllByProvince(Province province);
    // Spring Data JPA còn hỗ trợ thêm tự truy vấn đến cơ sở dữ liệu theo tên
    // như tên tương đương với câu truy vấn: select * from customer where province_id = ?
}
