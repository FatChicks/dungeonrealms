package org.dungeonrealms.game.shop.virtual;

import org.dungeonrealms.game.shop.MarketQuery;
import org.dungeonrealms.game.shop.ShopCategory;

/**
 * Created by Dr. Nick Doran on 8/6/2016.
 *
 * @apiNote Will query all players shops to form an interactive GUI
 * with the ability to query/purchase items.
 */
public class VirtualMarket {

    public void test() {

        execQuery(new MarketQuery[]{
                new MarketQuery().Select().All().Where().CategoryEquals(ShopCategory.AXE).And().PriceGreaterThanEqual(20)
        });

    }


    public void execQuery(MarketQuery[] args) {

    }

}
