export class QuestDetail {
    id: number;
    challengeTitle: string;
    challengeDesc: string;
    problemStat: string;
    inputFormat: string;
    constraints: string;
    outputFormat: string;
    maxScore: number;
    maxRuntime: number;
    progLang: string;
    solutionUrl: string;
    level: number;
    rating: number;

    constructor() {
        this.id = null;
        this.challengeTitle = '';
        this.challengeDesc = '';
        this.problemStat = '';
        this.inputFormat = '';
        this.constraints = '';
        this.outputFormat = '';
        this.maxScore = null;
        this.maxRuntime = null;
        this.progLang = '';
        this.solutionUrl = '';
        this.level = null;
        this.rating = null;
    }
}
