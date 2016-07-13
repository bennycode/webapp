var assets = require('gulp-bower-assets');
var bower = require('gulp-bower');
var browserSync = require('browser-sync');
var gulp = require('gulp');
var gulpTypings = require('gulp-typings');
var gutil = require('gulp-util');
var header = require('gulp-header');
var karma = require('karma');
var merge = require('merge2');
var rename = require('gulp-rename');
var runSequence = require('run-sequence');
var sass = require('gulp-sass');
var ts = require('gulp-typescript');
var tslint = require('gulp-tslint');

var paths = {
  src_main_sass: 'src/main/sass',
  src_main_ts: 'src/main/ts',
  src_main_typings: 'src/main/typings',
  src_main_webapp: 'src/main/webapp',
  src_main_webapp_scripts: 'src/main/webapp/scripts',
  src_main_webapp_style: 'src/main/webapp/style',
  src_main_webapp_style_lib: 'src/main/webapp/style/lib'
};

gulp.task('dev', ['default'], function() {
  gulp.watch(paths.src_main_sass + '/**/*.scss', ['sass']);
  gulp.watch(paths.src_main_webapp + '/**/*.*')
    .on('change', browserSync.reload);

  browserSync.init({
    port: 3636,
    server: {baseDir: './'},
    startPath: '/' + paths.src_main_webapp
  });
});

gulp.task('default', ['sass'], function() {
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

gulp.task('bower_install', function() {
  return bower({cmd: 'install'});
});

gulp.task('bower', function() {
  return gulp.src('bower-assets.json')
    .pipe(assets({prefix: false}))
    .pipe(gulp.dest(paths.src_main_webapp_style_lib));
});
