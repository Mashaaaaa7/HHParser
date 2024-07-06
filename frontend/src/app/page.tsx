import { Card, Table } from "./_components";

import { getVacancies } from "./_actions";

export default async function MainPage(
  { searchParams }: {
    searchParams?: {
      [key: string]: string
    }
  }
) {
  const data = await getVacancies(searchParams);

  return (
    <Card
      title="Список вакансий"
      description="Поможет найти работу и кушать в TanukiFamily"
    >
      <Table data={data} />
    </Card>
  );
}
