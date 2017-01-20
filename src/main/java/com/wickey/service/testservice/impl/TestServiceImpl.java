package com.wickey.service.testservice.impl;

import com.wickey.bean.testbean.TestBean;
import com.wickey.repository.RedisTestRepository;
import com.wickey.service.testservice.TestService;
import javassist.NotFoundException;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by fatboyliang on 2016/8/22.
 * 数据处理类
 */
@Service
public class TestServiceImpl implements TestService {

    //这里的单引号不能少，否则会报错，被识别是一个对象;
    public static final String CACHE_KEY = "'redisTestBean'";

    /**
     * value属性表示使用哪个缓存策略，缓存策略在ehcache.xml
     */
    public static final String DEMO_CACHE_NAME = "demo";

    @Resource
    private RedisTestRepository redisTestRepository;

    @Resource
    private RedisTemplate<String,String> redisTemplate;


    @Cacheable(value = DEMO_CACHE_NAME,key="'redisTestBean_'+#id")
    @Override
    public TestBean findById(long id) {
        System.out.println("没有走缓存！"+id);
        System.out.println("RedisTestServiceImpl.findById()=========从数据库中进行获取的....id="+id);
        return redisTestRepository.findOne(id);
    }

    @CacheEvict(value = "redisTestBean")
    @Override
    public void deleteFromCache(long id) {
        System.out.println("DemoInfoServiceImpl.delete().从缓存中删除.");
        redisTestRepository.delete(id);
    }

    @Override
    public void test() {

        ValueOperations<String,String> valueOperations = redisTemplate.opsForValue();
        valueOperations.set("mykey","random="+Math.random());
        System.out.println(valueOperations.get("mykey"));

    }

    /**
     *
     * 保存数据
     * @param rtb
     * @return
     */
    @CacheEvict(value = DEMO_CACHE_NAME,key = CACHE_KEY)
    @Override
    public TestBean save(TestBean rtb) {
        return redisTestRepository.save(rtb);
    }





    /**
     * http://www.mincoder.com/article/2096.shtml:
     *
     * 修改数据.
     *
     * 在支持Spring Cache的环境下，对于使用@Cacheable标注的方法，
     * Spring在每次执行前都会检查Cache中是否存在相同key的缓存元素，
     * 如果存在就不再执行该方法，而是直接从缓存中获取结果进行返回，
     * 否则才会执行并将返回结果存入指定的缓存中。@CachePut也可以声明一个方法支持缓存功能。
     * 与@Cacheable不同的是使用@CachePut标注的方法在执行前不会去检查缓存中是否存在之前执行过的结果，
     * 而是每次都会执行该方法，并将执行结果以键值对的形式存入指定的缓存中。
     * @CachePut也可以标注在类上和方法上。使用@CachePut时我们可以指定的属性跟@Cacheable是一样的。
     *
     * @param rtb
     * @return
     *
     * @throws NotFoundException
     */
    @CachePut(value = DEMO_CACHE_NAME,key = "'demoInfo_'+#rtb.getId()")
    //@CacheEvict(value = DEMO_CACHE_NAME,key = "'demoInfo_'+#updated.getId()")//这是清除缓存.
    @Override
    public TestBean update(TestBean rtb) throws NotFoundException {
        TestBean rtbOld = redisTestRepository.findOne(rtb.getId());
        if(rtbOld == null){
            throw new NotFoundException("no find");
        }
        rtbOld.setName(rtb.getName());
        rtbOld.setPwd(rtb.getPwd());
        return rtbOld;

    }



    /**
     * 删除数据.
     * @param id
     */
    @CacheEvict(value = DEMO_CACHE_NAME,key = "'demoInfo_'+#id")//这是清除缓存.
    @Override
    public void delete(Long id){
        redisTestRepository.delete(id);
    }


}
