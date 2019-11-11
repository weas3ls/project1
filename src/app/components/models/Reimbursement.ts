export interface Reimbursement {
    id: number;
    amount: number;
    description: string;
    requestee_id: number;
    resolver_id: number;
    status: string;
    type: string;
    date_submitted: Date;
    date_resolved: Date;
}