package com.neil.ecoblue.controller;

import com.neil.ecoblue.model.Account;
import com.neil.ecoblue.model.Item;
import com.neil.ecoblue.model.Redeem;
import com.neil.ecoblue.model.Transaction;
import com.neil.ecoblue.repository.AccountRepository;
import com.neil.ecoblue.repository.ItemRepository;
import com.neil.ecoblue.repository.RedeemRepository;
import com.neil.ecoblue.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller()
public class EcoblueController {

    //siya bahala ng lahat ng Account sa database
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    RedeemRepository redeemRepository;
    @Autowired
    TransactionRepository transactionRepository;

    @GetMapping("/")
    public String loadPage(){
        return "index";
    }

    @GetMapping("/logout")
    public ModelAndView logout(HttpSession session){
        //Check if the user is already logged in when accessing the login url. or onclick on banner/brand    /// redirect to landing page
        ModelAndView mav = new ModelAndView();
        mav.setViewName("index");
        session.invalidate();
        return mav;
    }

    @GetMapping("/login")
    public ModelAndView bannerClick(HttpSession session){
        //Check if the user is already logged in when accessing the login url. or onclick on banner/brand    /// redirect to landing page
        ModelAndView mav = new ModelAndView();
        mav.setViewName("index");
        Account account = (Account) session.getAttribute("account");
        if(account==null){
            return new ModelAndView("index");
        }
        else{
            if(account.getType()==1)
                mav.setViewName("admin");
            else
                mav.setViewName("student");
        }
        return mav;
    }

    @GetMapping("/convert")
    public ModelAndView showItems(HttpSession session){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("convert");
        mav.addObject("items", itemRepository.findAll());
        Account account = (Account) session.getAttribute("account");
        if(account==null){
            return new ModelAndView("index");
        }
        return mav;
    }

    @GetMapping("/redeem")
    public ModelAndView showRedeem(HttpSession session){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("redeem");
        mav.addObject("items", redeemRepository.findAll());
        Account account = (Account) session.getAttribute("account");
        if(account==null){
            return new ModelAndView("index");
        }
        return mav;
    }

    @GetMapping
    public ModelAndView showHistory(HttpSession session){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("history");
        Account account = (Account) session.getAttribute("account");
        if(account==null){
            return new ModelAndView("index");
        }
        mav.addObject("list", transactionRepository.findByAccountAccountId(account.getAccountId()));
        return mav;
    }

    @PostMapping("/convert")
    public ModelAndView convertItems(HttpServletRequest request, HttpSession session){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("convert");
        mav.addObject("items", itemRepository.findAll());
        Account account = (Account) session.getAttribute("account");
        if(account==null){
            return new ModelAndView("index");
        }
        Account acc = accountRepository.findByAccountId(account.getAccountId());
        Item item = itemRepository.findByItemId(Integer.parseInt(request.getParameter("itemType")));
        acc.setTotalPoints(acc.getTotalPoints() + item.getPhpValue()*Integer.parseInt(request.getParameter("qty")));
        accountRepository.save(acc);
        transactionRepository.save(new Transaction(acc,item,Integer.parseInt(request.getParameter("qty")),item.getPhpValue()*Integer.parseInt(request.getParameter("qty"))));
        session.setAttribute("account", acc);
        if(account==null){
            mav.setViewName("index");
        }
        return mav;
    }

    @PostMapping("/redeem")
    public ModelAndView redeemItems(HttpServletRequest request, HttpSession session){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("redeem");
        mav.addObject("items", redeemRepository.findAll());
        Account account = (Account) session.getAttribute("account");
        if(account==null){
            return new ModelAndView("index");
        }
        Redeem redeem = redeemRepository.findByRedeemId(Integer.parseInt(request.getParameter("itemType")));
        Account acc = accountRepository.findByAccountId(account.getAccountId());
        acc.setTotalPoints(acc.getTotalPoints() - redeem.getRedeemValue()*Integer.parseInt(request.getParameter("qty")));
        accountRepository.save(acc);
        transactionRepository.save(new Transaction(acc,redeem,Integer.parseInt(request.getParameter("qty")),redeem.getRedeemValue()*Integer.parseInt(request.getParameter("qty"))));
        session.setAttribute("account", acc);
        if(account==null){
            mav.setViewName("index");
        }
        return mav;
    }

    @PostMapping("/login")
    public ModelAndView login(@RequestParam(value = "email") String email, @RequestParam(value = "password") String password, HttpSession session){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("index");   //set to index.jsp
        Account account = accountRepository.findByEmailAndPassword(email, password);
        if(account!=null){
            if(account.getType()==1)
                mav.setViewName("admin");   //set return to admin.jsp (CMS)
            else
                mav.setViewName("student"); //student.jsp (Student Dashboard)
            session.setAttribute("account", account);
        }
        else
            return new ModelAndView("index");
        return mav;
    }

}
