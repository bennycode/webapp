var browserify = require('gulp-browserify');
var browserSync = require('browser-sync');
var gulp = require('gulp');
var gulpTypings = require('gulp-typings');
var gutil = require('gulp-util');
var header = require('gulp-header');
var merge = require('merge2');
var rename = require('gulp-rename');
var runSequence = require('run-sequence');
var sass = require('gulp-sass');
var Server = require('karma');
var ts = require('gulp-typescript');
var tslint = require('gulp-tslint');

var paths = {
  src_main_sass: 'src/main/sass',
  src_main_ts: 'src/main/ts',
  src_main_typings: 'src/main/typings',
  src_main_webapp: 'src/main/webapp',
  src_main_webapp_scripts: 'src/main/webapp/scripts',
  src_main_webapp_style: 'src/main/webapp/style'
};

gulp.task('build', ['sass'], function() {
  gutil.log('Finished build process');
});

gulp.task('sass', function() {
  return gulp.src(paths.src_main_sass + '/**/*.scss')
    .pipe(sass().on('error', sass.logError))
    .pipe(gulp.dest(paths.src_main_webapp_style));
});

gulp.task('typings', function() {
  return gulp.src('typings.json')
    .pipe(gulpTypings())
    .pipe(gulp.dest(paths.src_main_typings));
});
