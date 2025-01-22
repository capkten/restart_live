package org.capten.live.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.capten.live.model.Config;
import org.capten.live.model.ConfigExample;

public interface ConfigMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.config
     *
     * @mbg.generated Wed Jan 22 22:27:35 CST 2025
     */
    long countByExample(ConfigExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.config
     *
     * @mbg.generated Wed Jan 22 22:27:35 CST 2025
     */
    int deleteByExample(ConfigExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.config
     *
     * @mbg.generated Wed Jan 22 22:27:35 CST 2025
     */
    int deleteByPrimaryKey(Object id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.config
     *
     * @mbg.generated Wed Jan 22 22:27:35 CST 2025
     */
    int insert(Config row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.config
     *
     * @mbg.generated Wed Jan 22 22:27:35 CST 2025
     */
    int insertSelective(Config row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.config
     *
     * @mbg.generated Wed Jan 22 22:27:35 CST 2025
     */
    List<Config> selectByExample(ConfigExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.config
     *
     * @mbg.generated Wed Jan 22 22:27:35 CST 2025
     */
    Config selectByPrimaryKey(Object id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.config
     *
     * @mbg.generated Wed Jan 22 22:27:35 CST 2025
     */
    int updateByExampleSelective(@Param("row") Config row, @Param("example") ConfigExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.config
     *
     * @mbg.generated Wed Jan 22 22:27:35 CST 2025
     */
    int updateByExample(@Param("row") Config row, @Param("example") ConfigExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.config
     *
     * @mbg.generated Wed Jan 22 22:27:35 CST 2025
     */
    int updateByPrimaryKeySelective(Config row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.config
     *
     * @mbg.generated Wed Jan 22 22:27:35 CST 2025
     */
    int updateByPrimaryKey(Config row);

    /**
     * get Config by username
     * @param username
     * @return
     */
    Config getConfigByUserName(String username);
}