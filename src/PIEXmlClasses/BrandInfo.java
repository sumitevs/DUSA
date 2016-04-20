/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIEXmlClasses;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Sumit_Saseendran
 */
public class BrandInfo {

    public BrandInfo() {
        brandInfoList = new ArrayList<>();
    }

    public List<BrandInfoItem> getBrandInfoList() {
        if (brandInfoList == null) {
            brandInfoList = new ArrayList<>();
        }
        return brandInfoList;
    }

    private List<BrandInfoItem> brandInfoList;

    public void removeItem(String key) {
        Iterator<BrandInfoItem> it = brandInfoList.iterator();
        while (it.hasNext()) {
            BrandInfoItem bItem = it.next();
            if (bItem.getKey().equals(key)) {
                it.remove();
                break;
            }
        }
    }
}
