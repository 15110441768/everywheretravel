package com.example.lenovo.everywheretravel.utils;

import android.database.sqlite.SQLiteDatabase;

import com.example.lenovo.everywheretravel.base.BaseApp;
import com.example.lenovo.everywheretravel.dao.BanmiBeanDao;
import com.example.lenovo.everywheretravel.dao.DaoMaster;
import com.example.lenovo.everywheretravel.dao.DaoSession;
import com.example.lenovo.everywheretravel.bean.BanmiBean;

import java.util.List;

public class DbUtil {

    private final BanmiBeanDao banmiBeanDao;
    private final SQLiteDatabase db;

    private DbUtil() {
        //1.创建数据库
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(BaseApp.getInstance(), "com.example.lenovo.everywheretravel.db");
        db = devOpenHelper.getWritableDatabase();
        //2.设置读写权限
        DaoMaster daoMaster = new DaoMaster(db);

        //3.获取管理器类
        DaoSession daoSession = daoMaster.newSession();


        //4.获取dao操作对象
        banmiBeanDao = daoSession.getBanmiBeanDao();
    }

    private static DbUtil dbUtil;

    public static DbUtil getDbUtil() {
        if (dbUtil == null) {
            synchronized (DbUtil.class) {
                if (dbUtil == null) {
                    dbUtil = new DbUtil();
                }
            }
        }
        return dbUtil;
    }

    public void insert(BanmiBean banmiBean){
        BanmiBean queryOne = queryOne(banmiBean.getName());
        if (queryOne==null){
            banmiBeanDao.insert(banmiBean);
        }
    }

    public void delete(BanmiBean banmiBean){
        db.delete(banmiBeanDao.getTablename(),"name=?",new String[]{banmiBean.getName()});
    }

    public List<BanmiBean> queryAll(){
        List<BanmiBean> banmiBeans = banmiBeanDao.loadAll();
        return banmiBeans;
    }

    public BanmiBean queryOne(String name){
        BanmiBean banmiBean = banmiBeanDao.queryBuilder()
                .where(BanmiBeanDao.Properties.Name.eq(name))
                .build()
                .unique();
        return banmiBean;
    }
}
