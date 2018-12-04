export class ScoringModel {
    challengeId: string;
    challengeTitle: string;
    userId: string;
    challengeScore: number;

    constructor(cId, cTitle, uId, score) {
        this.challengeId = cId;
        this.challengeTitle = cTitle;
        this.userId = uId;
        this.challengeScore = score;
    }
}
