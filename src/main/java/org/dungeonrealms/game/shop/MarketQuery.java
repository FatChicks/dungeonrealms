package org.dungeonrealms.game.shop;

/**
 * Created by Dr. Nick Doran on 7/23/2016.
 */
public class MarketQuery {

    private String query;

    public MarketQuery() {
        query = "";
    }

    public MarketQuery Parenthesis(Object... obj) {
        query += "(";
        for (Object o : obj) {
            query += o + ",";
        }
        query = query.endsWith(",") ? query.substring(0, query.lastIndexOf(",")) : query;
        query += ")" + " ";
        return this;
    }

    public MarketQuery Values(Object... obj) {
        query += "VALUES" + " ";
        query += "(";
        for (Object o : obj) {
            query += o + ",";
        }
        query = query.endsWith(",") ? query.substring(0, query.lastIndexOf(",")) : query;
        query += ")" + " ";
        return this;
    }

    public MarketQuery Into() {
        query += "INTO" + " ";
        return this;
    }

    public MarketQuery Select() {
        query += "SELECT" + " ";
        return this;
    }

    public MarketQuery All() {
        query += "*" + " ";
        return this;
    }

    public MarketQuery Where() {
        query += "WHERE" + " ";
        return this;
    }

    public MarketQuery From() {
        query += "FROM" + " ";
        return this;
    }

    public MarketQuery PriceLessThan(int par1) {
        query += "price < " + par1;
        return this;
    }

    public MarketQuery PriceLessThanEqual(int par1) {
        query += "price <= " + par1;
        return this;
    }

    public MarketQuery PriceGreaterThan(int par1) {
        query += "price > " + par1;
        return this;
    }

    public MarketQuery PriceGreaterThanEqual(int par1) {
        query += "price >= " + par1;
        return this;
    }

    public MarketQuery And() {
        query += "AND";
        return this;
    }

    public MarketQuery CategoryEquals(ShopCategory category) {
        query += "category" + " " + "=" + " ";
        query += "'" + category.getName() + "'";
        return this;
    }

    public MarketQuery Table(String par1) {
        query += "`" + par1 + "`" + " ";
        return this;
    }

    public MarketQuery Field(Object par1) {
        query += par1 + " ";
        return this;
    }

    public MarketQuery asString(String par1) {
        if (par1.contains("'")) {
            query += par1 + " ";
            return this;
        } else {
            query += "'" + par1 + "'" + " ";
            return this;
        }
    }

    public MarketQuery asInt(int par1) {
        query += par1 + " ";
        return this;
    }

    public MarketQuery Equals() {
        query += "=" + " ";
        return this;
    }

    public MarketQuery End() {
        query += ";";
        return this;
    }

    public String getQuery() {
        return query.trim();
    }

}
