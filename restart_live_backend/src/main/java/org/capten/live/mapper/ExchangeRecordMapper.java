package org.capten.live.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.capten.live.model.ExchangeRecord;
import org.capten.live.model.ExchangeRecordExample;

public interface ExchangeRecordMapper extends BaseMapper<ExchangeRecord> {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.exchange_record
     *
     * @mbg.generated Wed Jan 22 22:27:35 CST 2025
     */
    long countByExample(ExchangeRecordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.exchange_record
     *
     * @mbg.generated Wed Jan 22 22:27:35 CST 2025
     */
    int deleteByExample(ExchangeRecordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.exchange_record
     *
     * @mbg.generated Wed Jan 22 22:27:35 CST 2025
     */
    int deleteByPrimaryKey(Object id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.exchange_record
     *
     * @mbg.generated Wed Jan 22 22:27:35 CST 2025
     */
    int insert(ExchangeRecord row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.exchange_record
     *
     * @mbg.generated Wed Jan 22 22:27:35 CST 2025
     */
    int insertSelective(ExchangeRecord row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.exchange_record
     *
     * @mbg.generated Wed Jan 22 22:27:35 CST 2025
     */
    List<ExchangeRecord> selectByExample(ExchangeRecordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.exchange_record
     *
     * @mbg.generated Wed Jan 22 22:27:35 CST 2025
     */
    ExchangeRecord selectByPrimaryKey(Object id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.exchange_record
     *
     * @mbg.generated Wed Jan 22 22:27:35 CST 2025
     */
    int updateByExampleSelective(@Param("row") ExchangeRecord row, @Param("example") ExchangeRecordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.exchange_record
     *
     * @mbg.generated Wed Jan 22 22:27:35 CST 2025
     */
    int updateByExample(@Param("row") ExchangeRecord row, @Param("example") ExchangeRecordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.exchange_record
     *
     * @mbg.generated Wed Jan 22 22:27:35 CST 2025
     */
    int updateByPrimaryKeySelective(ExchangeRecord row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.exchange_record
     *
     * @mbg.generated Wed Jan 22 22:27:35 CST 2025
     */
    int updateByPrimaryKey(ExchangeRecord row);
}