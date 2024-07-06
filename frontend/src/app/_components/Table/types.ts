export type TableItem = {
  id: number,
  name: string,
  schedule: string,
  salary: {
    from: number,
    to?: number,
    currency: string,
  },
  city: string,
  moreUrl: string,
  applyUrl: string
};

export type TableProps = {
  data: Array<TableItem>
};
