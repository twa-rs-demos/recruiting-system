'use strict';

var mongoose = require('mongoose');
var Schema = mongoose.Schema;
var constant = require('../mixin/constant');

var userHomeworkQuizzesSchema = new Schema({
  userId: Number,
  quizzes: [{
    id: Number,
    locked: Boolean,
    status: Number,
    startTime: Number,
    userAnswerRepo: String,
    message: String,
    branch: String,
    commitSHA: String
  }]
});

userHomeworkQuizzesSchema.statics.initUserHomeworkQuizzes = function (userId, idList, callback) {
  this.findOne({userId: userId}, (err, doc) => {
    if (doc) {
      callback(new Error('is exist'), null);
    } else {
      var quizzes = [];

      idList.forEach((id) => {
        quizzes.push({
          id: id,
          locked: true,
          status: 0
        });
      });

      this.create({
        userId: userId,
        quizzes: quizzes
      }, callback);
    }
  });
};

userHomeworkQuizzesSchema.statics.unlockNext = function (userId, callback) {
  this.findOne({userId: userId}, function (err, data) {
    if (err || !data) {
      callback(err || new Error('user is not allowed'));
    } else {
      var locked = 0;
      var success = 0;

      data.quizzes.forEach(function (quiz) {
        if (quiz.locked) {
          locked++;
        } else if (quiz.status === constant.homeworkQuizzesStatus.SUCCESS) {
          success++;
        }
      });

      if (data.quizzes.length === (locked + success)) {
        data.quizzes[success].status = 1;
        data.quizzes[success].locked = false;

        data.save(callback);
      }else {
        callback(null, true);
      }
    }
  });
};

userHomeworkQuizzesSchema.statics.findProgressTasks = function (callback) {
  this.find({quizzes: {$elemMatch: {status: 2}}}, 'userId quizzes', (err, doc) => {
    if (err){
      callback(err);
    }else {
      var result = [];

      doc.forEach((item) => {
        var userAnswerRepo;
        var quizId;
        item.quizzes.forEach((quiz) => {
          userAnswerRepo = quiz.status === 2 ? quiz.userAnswerRepo : userAnswerRepo;
          quizId = quiz.status === 2 ? quiz.id : quizId;
        });

        result.push({
          userId: item.userId,
          quizId: quizId,
          userAnswerRepo: userAnswerRepo
        });
      });

      callback(null, result);
    }
  });
};

module.exports = mongoose.model('UserHomeworkQuizzes', userHomeworkQuizzesSchema);
