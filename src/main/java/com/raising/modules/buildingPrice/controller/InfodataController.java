package com.raising.modules.buildingPrice.controller;

import com.raising.framework.entity.ResultCode;
import com.raising.modules.buildingPrice.entity.BuildColBrowEntity;
import com.raising.modules.buildingPrice.entity.LoupanPicEntity;
import com.raising.modules.buildingPrice.entity.QueryInfoData;
import com.raising.modules.buildingPrice.service.BuildColBrowService;
import com.raising.modules.buildingPrice.service.LoupanPicService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.raising.framework.controller.BaseController;
import com.raising.framework.entity.ResultVo;
import com.raising.framework.mybaits.Page;

import com.raising.modules.buildingPrice.entity.InfodataEntity;
import com.raising.modules.buildingPrice.service.InfodataService;

import java.util.*;

/**
 * 控制器
 *
 * @author fsd
 * @createTime 2019-03-04 14:17:51
 */
@RestController
@RequestMapping("/buildingPrice/infodata")
public class InfodataController extends BaseController {

    @Autowired
    private InfodataService infodataService;
    @Autowired
    private BuildColBrowService buildColBrowService;
    @Autowired
    private LoupanPicService loupanPicService;

    /**
     * 分页 - 查询
     *
     * @param page
     * @param infodata
     * @return ResultVo
     * @author fsd
     * @datetime 2019-03-04 14:17:51
     */
    // @RequiresPermissions("buildingPrice:infodata:select")
    @GetMapping("/page")
    public ResultVo page(QueryInfoData infodata, Page<QueryInfoData> page) {
        page.setEntity(infodata);
//        HashMap<String, String> map = new HashMap<>();
//        map.put("startPrice", startPrice);
//        map.put("endPrice", endPrice);
//        map.put("startArea", startArea);
//        map.put("endArea", endArea);
        ResultVo resultVo = infodataService.getPageByPriceArea(page);
        return resultVo;
    }

    /**
     * 详情 - 查询
     *
     * @param id
     * @return ResultVo
     * @author fsd
     * @datetime 2019-03-04 14:17:51
     */
    // @RequiresPermissions("buildingPrice:infodata:select")
    @GetMapping("/info")
    public ResultVo info(@RequestParam("id") String id) {

        InfodataEntity info = (InfodataEntity) infodataService.get(id).getData();
        BuildColBrowEntity buildColBrowEntity = new BuildColBrowEntity();
        buildColBrowEntity.setXiaoquid(id);
        ResultVo resultVo = buildColBrowService.increaseBrowse(buildColBrowEntity);
        if (resultVo.getCode() != ResultCode.OK.getCode()) {
            return resultVo;
        } else {
            ResultVo re2 = loupanPicService.getListPicByUrl(info.getUrl());
            if (re2.getCode() == 13) {
                return re2;
            }
            List<LoupanPicEntity> loupanPicEntities = (List<LoupanPicEntity>) re2.getData();
            BuildColBrowEntity buildColBrowEntity1 = (BuildColBrowEntity) buildColBrowService.get(id).getData();

            HashMap<String, Object> result2view = new HashMap<>();
            result2view.put("buildInfo", info);
            result2view.put("picList",loupanPicEntities);
            result2view.put("coll", buildColBrowEntity1);
            resultVo.setData(result2view);
            return resultVo;
        }


    }

    /**
     * 新增 - 插入
     *
     * @param infodata
     * @return ResultVo
     * @author fsd
     * @datetime 2019-03-04 14:17:51
     */
    // @RequiresPermissions("buildingPrice:infodata:insert")
    @PostMapping("/insert")
    public ResultVo insert(InfodataEntity infodata) {
        InfodataEntity insert = new InfodataEntity();
        //赋值至 insert 对象
        insert.setDate(infodata.getDate());
        insert.setCity(infodata.getCity());
        insert.setRegion(infodata.getRegion());
        insert.setXiaoqu(infodata.getXiaoqu());
        insert.setPrice(infodata.getPrice());
        insert.setTotal(infodata.getTotal());
        insert.setUrl(infodata.getUrl());
        insert.setPropertytype(infodata.getPropertytype());
        insert.setReferenceprice(infodata.getReferenceprice());
        insert.setProjectfeatures(infodata.getProjectfeatures());
        insert.setRegionallocation(infodata.getRegionallocation());
        insert.setPropertyaddress(infodata.getPropertyaddress());
        insert.setSalesofficeaddress(infodata.getSalesofficeaddress());
        insert.setDeveloper(infodata.getDeveloper());
        insert.setBuildingtype(infodata.getBuildingtype());
        insert.setLandscapingratio(infodata.getLandscapingratio());
        insert.setSitearea(infodata.getSitearea());
        insert.setFloorarearatio(infodata.getFloorarearatio());
        insert.setBuildingarea(infodata.getBuildingarea());
        insert.setYearofpropertyrights(infodata.getYearofpropertyrights());
        insert.setNumplan(infodata.getNumplan());
        insert.setDesigntype(infodata.getDesigntype());
        insert.setPropertycompany(infodata.getPropertycompany());
        insert.setParkingratio(infodata.getParkingratio());
        insert.setPropertycosts(infodata.getPropertycosts());
        insert.setHeatingmethod(infodata.getHeatingmethod());
        insert.setWatersupplymethod(infodata.getWatersupplymethod());
        insert.setPowersupply(infodata.getPowersupply());
        insert.setParkingspace(infodata.getParkingspace());
        insert.setNearby(infodata.getNearby());
        insert.setArea(infodata.getArea());
        insert.setId(infodata.getId());
        ResultVo resultVo = infodataService.insert(insert);
        resultVo.setData(insert.getId());
        return resultVo;
    }

    /**
     * 更新
     *
     * @param infodata
     * @return ResultVo
     * @author fsd
     * @datetime 2019-03-04 14:17:51
     */
    // @RequiresPermissions("buildingPrice:infodata:update")
    @PostMapping("/update")
    public ResultVo update(InfodataEntity infodata) {
        InfodataEntity update = new InfodataEntity();
        //赋值至 update 对象
        update.setDate(infodata.getDate());
        update.setCity(infodata.getCity());
        update.setRegion(infodata.getRegion());
        update.setXiaoqu(infodata.getXiaoqu());
        update.setPrice(infodata.getPrice());
        update.setTotal(infodata.getTotal());
        update.setUrl(infodata.getUrl());
        update.setPropertytype(infodata.getPropertytype());
        update.setReferenceprice(infodata.getReferenceprice());
        update.setProjectfeatures(infodata.getProjectfeatures());
        update.setRegionallocation(infodata.getRegionallocation());
        update.setPropertyaddress(infodata.getPropertyaddress());
        update.setSalesofficeaddress(infodata.getSalesofficeaddress());
        update.setDeveloper(infodata.getDeveloper());
        update.setBuildingtype(infodata.getBuildingtype());
        update.setLandscapingratio(infodata.getLandscapingratio());
        update.setSitearea(infodata.getSitearea());
        update.setFloorarearatio(infodata.getFloorarearatio());
        update.setBuildingarea(infodata.getBuildingarea());
        update.setYearofpropertyrights(infodata.getYearofpropertyrights());
        update.setNumplan(infodata.getNumplan());
        update.setDesigntype(infodata.getDesigntype());
        update.setPropertycompany(infodata.getPropertycompany());
        update.setParkingratio(infodata.getParkingratio());
        update.setPropertycosts(infodata.getPropertycosts());
        update.setHeatingmethod(infodata.getHeatingmethod());
        update.setWatersupplymethod(infodata.getWatersupplymethod());
        update.setPowersupply(infodata.getPowersupply());
        update.setParkingspace(infodata.getParkingspace());
        update.setNearby(infodata.getNearby());
        update.setArea(infodata.getArea());
        update.setId(infodata.getId());
        return infodataService.update(update);
    }

    /**
     * 删除
     *
     * @param id
     * @return ResultVo
     * @author fsd
     * @datetime 2019-03-04 14:17:51
     */
    // @RequiresPermissions("buildingPrice:infodata:delete")
    @PostMapping("/delete")
    public ResultVo delete(@RequestParam("id") String id) {
        return infodataService.delete(id);
    }


    /**
     * 给出一个楼盘  同一区域 的相似房源
     * @author fsd
     * @param infodataEntity
     * @return
     */
    @GetMapping("/getSimilarByOneLoupan")
    public ResultVo page(InfodataEntity infodataEntity) {

        //1、相似属性：获取当前小区的城市，区，房屋类别，价格，面积，特征短语
        String cityName = infodataEntity.getCity();
        String regionName = infodataEntity.getRegion();
        String propertyType = infodataEntity.getPropertytype();
        String features = infodataEntity.getProjectfeatures();
        String price = infodataEntity.getPrice();
        String area = infodataEntity.getArea();
        String[] featureList = features.split(" ");
        //2、初筛：先按照城市,户型,价格区间，面积区间 初筛，得到一个候选楼盘list  (因为用户偏向于查看同一个城市同一类型的 楼盘)
        QueryInfoData queryInfoData = new QueryInfoData();
        queryInfoData.setCity(cityName);
        queryInfoData.setPropertytype(propertyType);

        if (area!=null && !area.equals("暂无信息")){
            queryInfoData.setStartArea(Integer.valueOf(area)-50);
            queryInfoData.setEndArea(Integer.valueOf(area)+50);
        }
        if (price!=null && !price.equals("暂无信息")){
            queryInfoData.setStartPrice(Integer.valueOf(price)-10000);
            queryInfoData.setEndPrice(Integer.valueOf(price)+10000);
        }
        //获取bestNum 个相似楼盘信息
        ResultVo resultVoquery = infodataService.getSimilarLoupanByOneLoupan(queryInfoData,featureList, 6);
        //存入字典返回，格式为 小区名：相似楼盘list
        List<InfodataEntity> resultList = (List<InfodataEntity>)resultVoquery.getData();
        Map<String,List<InfodataEntity>> result = new HashMap<>();
        result.put(infodataEntity.getXiaoqu(), resultList);
        return new ResultVo(ResultCode.OK, result);
    }
}
