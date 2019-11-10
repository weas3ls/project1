export interface Reimbursement {
    id: number;
    amount: number;
    description: String;
    requestee_id: number;
    resolver_id: number;
    status_id: number;
    type_id: number;
    date_submitted: Date;
    date_resolved: Date;
}