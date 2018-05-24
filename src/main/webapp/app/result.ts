import { Answer }  from './answer.js';

export class Result {
  id: number;
  testId: number;
  testName: string;
  testTaken: Date;
  testStartTime: string;
  supervisedValue: boolean;
  evaluation: number;
  userId: number;
  answerList: Answer[];
}

