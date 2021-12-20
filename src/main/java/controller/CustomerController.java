package controller;

import model.Customer;
import model.Province;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import service.customer.ICustomerService;
import service.province.IProvinceService;

import java.util.Optional;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    // gọi ra service đã tiên
    @Autowired
    private ICustomerService customerService;

    @Autowired
    private IProvinceService provinceService;

    // khai báo một biến mà sẽ dùng ở nhiều view, mỗi khi view gọi đến "provinces" dữ liệu sẽ tự đổ ra
    @ModelAttribute("provinces")
    public Iterable<Province> provinces(){
        return provinceService.findAll();
    }


    @GetMapping("create")
    public String showCreateForm() {
        return "/customer/create";
    }

    @PostMapping("create")
    public String saveCustomer(@ModelAttribute("customer") Customer customer, Long provinceId) {
        customerService.save(customer);
        return "redirect:customers";
    }

    @GetMapping("")
    public String listCustomers(Model model) {
        Iterable<Customer> customers = customerService.findAll();
        model.addAttribute("customers", customers);
        return "/customer/list";
    }

    @GetMapping("edit/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        Optional<Customer> customer = customerService.findById(id);
        if (customer.isPresent()) { // kiểm tra xem dữ liệu có null không
            ModelAndView modelAndView = new ModelAndView("/customer/edit");
            modelAndView.addObject("customer", customer.get());
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("edit")
    public ModelAndView updateCustomer(@ModelAttribute("customer") Customer customer) {
        customerService.save(customer);
        ModelAndView modelAndView = new ModelAndView("/customer/edit");
        modelAndView.addObject("customer", customer);
        modelAndView.addObject("message", "Customer updated successfully");
        return modelAndView;
    }

    @GetMapping("delete/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id) {
        Optional<Customer> customer = customerService.findById(id);
        if (customer.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("/customer/delete");
            modelAndView.addObject("customer", customer.get());
            return modelAndView;

        } else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("delete")
    public String deleteCustomer(@ModelAttribute("customer") Customer customer) {
        customerService.remove(customer.getId());
        return "redirect:customers";
    }

}
