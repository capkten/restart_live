export default {
  plugins: {
    'postcss-pxtorem': {
      rootValue: 16, // 1rem = 16px
      propList: ['*'], // 转换所有属性
      unitPrecision: 5, // 保留小数点后5位
      selectorBlackList: [], // 不转换的选择器
      replace: true,
      mediaQuery: false, // 是否转换媒体查询中的px
      minPixelValue: 0, // 最小转换值
      exclude: /node_modules/i // 排除node_modules目录
    }
  }
}
