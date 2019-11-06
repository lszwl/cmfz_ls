package com.baizhi.Aop;

/*@Aspect
@Configuration  //配置*/
public class Advice {
/*
    @Autowired
    private Jedis jedis;


    @Around("execution(* com.baizhi.service.*.show(..))")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();

        Method method = methodSignature.getMethod();

        boolean b = method.isAnnotationPresent(RedisCache.class);
        Object result = null;
        if(b){
            Object[] args = proceedingJoinPoint.getArgs();
            Object target = proceedingJoinPoint.getTarget();

            String name = target.getClass().getName();//获取全类名

            String methodName = methodSignature.getName();//获取方法名

            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(name).append(".").append(methodName).append("(");


            for (int i=0;i<args.length;i++){
                stringBuilder.append(args[i]);
                System.out.println(args[i]);
                if(i==args.length-1){
                    break;
                }
                stringBuilder.append(",");
            }
            stringBuilder.append(")");
            if(jedis.exists(stringBuilder.toString())){
                String s = jedis.get(stringBuilder.toString());
                result = JSONObject.parse(s);
            }else {
                result = proceedingJoinPoint.proceed();
                jedis.set(stringBuilder.toString(),JSONObject.toJSONString(result));
            }

        }else {
            result = proceedingJoinPoint.proceed();
        }
        jedis.close();
        return result;
    }

    @AfterReturning("execution(* com.baizhi.service.*.*(..))&& !execution(* com.baizhi.service.*.show(..))")
    public void after(JoinPoint joinPoint){
        String name = joinPoint.getTarget().getClass().getName();
        System.out.println(name);
        jedis.flushDB();
        jedis.close();
    }*/
}
