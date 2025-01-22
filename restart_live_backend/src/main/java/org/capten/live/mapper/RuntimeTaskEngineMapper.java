package org.capten.live.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.capten.live.model.RuntimeTaskEngine;
import org.capten.live.model.RuntimeTaskEngineExample;

public interface RuntimeTaskEngineMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.runtime_task_engine
     *
     * @mbg.generated Wed Jan 22 22:27:35 CST 2025
     */
    long countByExample(RuntimeTaskEngineExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.runtime_task_engine
     *
     * @mbg.generated Wed Jan 22 22:27:35 CST 2025
     */
    int deleteByExample(RuntimeTaskEngineExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.runtime_task_engine
     *
     * @mbg.generated Wed Jan 22 22:27:35 CST 2025
     */
    int deleteByPrimaryKey(Object id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.runtime_task_engine
     *
     * @mbg.generated Wed Jan 22 22:27:35 CST 2025
     */
    int insert(RuntimeTaskEngine row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.runtime_task_engine
     *
     * @mbg.generated Wed Jan 22 22:27:35 CST 2025
     */
    int insertSelective(RuntimeTaskEngine row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.runtime_task_engine
     *
     * @mbg.generated Wed Jan 22 22:27:35 CST 2025
     */
    List<RuntimeTaskEngine> selectByExample(RuntimeTaskEngineExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.runtime_task_engine
     *
     * @mbg.generated Wed Jan 22 22:27:35 CST 2025
     */
    RuntimeTaskEngine selectByPrimaryKey(Object id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.runtime_task_engine
     *
     * @mbg.generated Wed Jan 22 22:27:35 CST 2025
     */
    int updateByExampleSelective(@Param("row") RuntimeTaskEngine row, @Param("example") RuntimeTaskEngineExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.runtime_task_engine
     *
     * @mbg.generated Wed Jan 22 22:27:35 CST 2025
     */
    int updateByExample(@Param("row") RuntimeTaskEngine row, @Param("example") RuntimeTaskEngineExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.runtime_task_engine
     *
     * @mbg.generated Wed Jan 22 22:27:35 CST 2025
     */
    int updateByPrimaryKeySelective(RuntimeTaskEngine row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.runtime_task_engine
     *
     * @mbg.generated Wed Jan 22 22:27:35 CST 2025
     */
    int updateByPrimaryKey(RuntimeTaskEngine row);
}