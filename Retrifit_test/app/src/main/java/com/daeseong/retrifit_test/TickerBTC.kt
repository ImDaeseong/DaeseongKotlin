package com.daeseong.retrifit_test
import com.google.gson.annotations.SerializedName

class TickerBTC {
    @SerializedName("status")
    private val status: String? = null

    @SerializedName("data")
    private val data: Data? = null

    fun getStatus(): String? {
        return status
    }

    fun getData(): Data? {
        return data
    }

    class Data {
        @SerializedName("opening_price")
        private val openingPrice: String? = null

        @SerializedName("closing_price")
        private val closingPrice: String? = null

        @SerializedName("min_price")
        private val minPrice: String? = null

        @SerializedName("max_price")
        private val maxPrice: String? = null

        @SerializedName("units_traded")
        private val unitsTraded: String? = null

        @SerializedName("acc_trade_value")
        private val accTradeValue: String? = null

        @SerializedName("prev_closing_price")
        private val prevClosingPrice: String? = null

        @SerializedName("units_traded_24H")
        private val unitsTraded24H: String? = null

        @SerializedName("acc_trade_value_24H")
        private val accTradeValue24H: String? = null

        @SerializedName("fluctate_24H")
        private val fluctate24H: String? = null

        @SerializedName("fluctate_rate_24H")
        private val fluctateRate24H: String? = null

        @SerializedName("date")
        private val date: String? = null

        fun getOpeningPrice(): String? {
            return openingPrice
        }

        fun getClosingPrice(): String? {
            return closingPrice
        }

        fun getMinPrice(): String? {
            return minPrice
        }

        fun getMaxPrice(): String? {
            return maxPrice
        }

        fun getUnitsTraded(): String? {
            return unitsTraded
        }

        fun getAccTradeValue(): String? {
            return accTradeValue
        }

        fun getPrevClosingPrice(): String? {
            return prevClosingPrice
        }

        fun getUnitsTraded24H(): String? {
            return unitsTraded24H
        }

        fun getAccTradeValue24H(): String? {
            return accTradeValue24H
        }

        fun getFluctate24H(): String? {
            return fluctate24H
        }

        fun getFluctateRate24H(): String? {
            return fluctateRate24H
        }

        fun getDate(): String? {
            return date
        }
    }
}
