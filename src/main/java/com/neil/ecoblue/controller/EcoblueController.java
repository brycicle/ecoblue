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

    //return lang sa index pag nagtype ng localhost:8080
    @GetMapping("/")
    public String loadPage(){
        return "index";
    }

    //logout----alam mo na yan
    @GetMapping("/logout")
    public ModelAndView logout(HttpSession session){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("index");
        session.invalidate();
        return mav;
    }

    //Check if the user is already logged in when accessing the login url. or onclick on banner/brand    /// redirect to landing page
    @GetMapping("/login")
    public ModelAndView bannerClick(HttpSession session){
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

    //ididisplay lang form na pwede ka magconvert ng items to points
    @GetMapping("/convert")
    public ModelAndView showItems(HttpSession session){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("convert");
        //kailangan para ma display mga items galing sa db papunta sa options
        mav.addObject("items", itemRepository.findAll());
        Account account = (Account) session.getAttribute("account");
        //pag di nakita account mo redirect ka login page
        if(account==null){
            return new ModelAndView("index");
        }
        return mav;
    }
    //ididisplay lang form na pwede ka magredeem ng items gamit points
    @GetMapping("/redeem")
    public ModelAndView showRedeem(HttpSession session){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("redeem");
        //ilalagay items na mga pwede mo ma redeem na galing sa db
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
        //kinukuha lahat ng transactions mo sa db based sa account id mo
        mav.addObject("list", transactionRepository.findByAccountAccountId(account.getAccountId()));
        return mav;
    }

    //dito pupunta pagkatapos mo i click yung convert button
    @PostMapping("/convert")
    public ModelAndView convertItems(HttpServletRequest request, HttpSession session){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("convert");
        mav.addObject("items", itemRepository.findAll());
        Account account = (Account) session.getAttribute("account");
        if(account==null){
            return new ModelAndView("index");
        }
        // 1. kukunin muna account mo galing sa db.... bawal kasi yung nasa session lang kasi isasave to sa transactions
        Account acc = accountRepository.findByAccountId(account.getAccountId());
        // 2. kukunin yung item type na icoconvert mo papunta sa points galing sa db
        Item item = itemRepository.findByItemId(Integer.parseInt(request.getParameter("itemType")));
        // 3. iadd na sa total points ng account yung points na nakuha mo sa pag convert
        acc.setTotalPoints(acc.getTotalPoints() + item.getPhpValue()*Integer.parseInt(request.getParameter("qty")));
        // 4. iuupdate yung account mo kasama yung bago mong points
        accountRepository.save(acc);
        // 5. isasave yung nangyari na transaction.... conversion type yung transaction
        transactionRepository.save(new Transaction(acc,item,Integer.parseInt(request.getParameter("qty")),item.getPhpValue()*Integer.parseInt(request.getParameter("qty"))));
        // 6. set attribute ulit ng account kasi nag update yung points ng account mo
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
        // 1. kukunin muna account mo galing sa db.... bawal kasi yung nasa session lang kasi isasave to sa transactions
        Account acc = accountRepository.findByAccountId(account.getAccountId());
        // 2. kukunin yung item type na ireredeem mo galing sa db
        Redeem redeem = redeemRepository.findByRedeemId(Integer.parseInt(request.getParameter("itemType")));
        // 3. idededuct yung points na ginamit mo
        acc.setTotalPoints(acc.getTotalPoints() - redeem.getRedeemValue()*Integer.parseInt(request.getParameter("qty")));
        // 4. update ulit ng account kasi nabawasan points
        accountRepository.save(acc);
        // 5. isasave yung nangyari na transaction.... redeem type yung transaction
        transactionRepository.save(new Transaction(acc,redeem,Integer.parseInt(request.getParameter("qty")),redeem.getRedeemValue()*Integer.parseInt(request.getParameter("qty"))));
        // 6. set attribute ulit ng account kasi nag update yung points ng account mo
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
        //hanap ng match sa db gamit email at password
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
