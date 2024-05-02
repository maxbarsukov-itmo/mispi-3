const path = require('path');

module.exports = {
  mode: 'development',
  entry: './src/main/webapp/resources/src/js/index.js',
  output: {
    filename: 'main.js',
    path: path.resolve(__dirname, "src/main/webapp/resources/target"),
    libraryTarget: 'var',
    library: 'MyLibrary'
  },
  module: {
    rules: [
      {
        test: /\.css$/i,
        use: ["style-loader", "css-loader"],
        exclude: [
          path.resolve(__dirname, "src/main/webapp/resources/src/js/clock.js"),
        ]
      },
    ],
  },
  optimization: {
    minimize: false
  },
};
