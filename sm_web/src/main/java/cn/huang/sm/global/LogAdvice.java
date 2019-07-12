package cn.huang.sm.global;

import cn.huang.sm.entity.Log;
import cn.huang.sm.entity.Staff;
import cn.huang.sm.service.LogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @Author: Yaking
 * @Date: 2019-07-12 11:49
 * @Describe:
 */
@Component
@Aspect
public class LogAdvice {
    @Autowired
    private LogService logService;

    /*    @After("execution(* cn.huang.sm.controller.*.* (..)) && !execution(* cn.huang.sm.controller.SelfController.*(..)) && !execution(* cn.huang.sm.controller.*.to*(..))")
        public void operationLog(JoinPoint joinPoint) {
            Log log = new Log();
            *//*joinPoint.getTarget():需要增强的目标对象 *//*
        log.getMoudle(joinPoint.getTarget().getClass().getSimpleName());
        *//*joinPoint.getSignature():获得方法的签名对象  ---->*.getName()可以获取方法名*//*
        log.setOperation(joinPoint.getSignature().getName());
        *//*通过 request 得到 session,之后用来获取操作源   getArgs()[0]:获取参数的第一个*//*
        HttpServletRequest request = (HttpServletRequest) joinPoint.getArgs()[0];
        HttpSession session = request.getSession();
        Object obj = session.getAttribute("USER");
        Staff staff = (Staff) obj;
        log.setOperator(staff.getAccount());
        log.setResult("成功");
        logService.addOperationLog(log);
    }*/
    @After("execution(* cn.huang.sm.controller.*.*(..)) && !execution(* cn.huang.sm.controller.SelfController.*(..))&&" +
            " !execution(* cn.huang.sm.controller.*.to*(..))")
    public void operationLog(JoinPoint joinPoint) {
        Log log = new Log();
        log.setMoudle(joinPoint.getTarget().getClass().getSimpleName());
        log.setOperation(joinPoint.getSignature().getName());
        HttpServletRequest request = (HttpServletRequest) joinPoint.getArgs()[0];
        HttpSession session = request.getSession();
        Object object = session.getAttribute("USER");
        Staff staff = (Staff) object;
        log.setOperator(staff.getAccount());
        log.setResult("成功");
        logService.addOperationLog(log);
    }

    @AfterThrowing(throwing = "e", pointcut = "execution(* cn.huang.sm.controller.*.*(..)) && !execution(* cn.huang.sm.controller.SelfController.*(..))")
    public void systemLog(JoinPoint joinPoint, Throwable e) {
        Log log = new Log();
        log.setMoudle(joinPoint.getTarget().getClass().getSimpleName());
        log.setOperation(joinPoint.getSignature().getName());
        HttpServletRequest request = (HttpServletRequest) joinPoint.getArgs()[0];
        HttpSession session = request.getSession();
        Object obj = session.getAttribute("USER");
        Staff staff = (Staff) obj;
        log.setOperator(staff.getAccount());
        log.setResult(e.getClass().getSimpleName());
        logService.addSystemLog(log);
    }

    @After("execution(* cn.huang.sm.controller.SelfController.login(..))")
    public void loginLog(JoinPoint joinPoint) {
        log(joinPoint);
    }

    @Before("execution(* cn.huang.sm.controller.SelfController.logout(..))")
    public void logoutLog(JoinPoint joinPoint) {
        log(joinPoint);
    }


    private void log(JoinPoint joinPoint) {
        Log log = new Log();
        log.setMoudle(joinPoint.getTarget().getClass().getSimpleName());
        log.setOperation(joinPoint.getSignature().getName());
        HttpServletRequest request = (HttpServletRequest) joinPoint.getArgs()[0];
        HttpSession session = request.getSession();
        Object obj = session.getAttribute("USER");
        if (obj == null) {
            log.setOperator(request.getParameter("account"));
            log.setResult("失败");
        } else {
            Staff staff = (Staff) obj;
            log.setOperator(staff.getAccount());
            log.setResult("成功");
        }
        logService.addLoginLog(log);
    }
}
