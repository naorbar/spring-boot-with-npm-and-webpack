const path = require('path');

module.exports = {
  entry: './client/index.js',
  output: {
	    path: path.resolve(__dirname, '../src/main/resources/', 'public'),
	    filename: '[name].bundle.js'
  },
  devtool: 'inline-source-map'
};