"use server";

import { TableItem } from "../_components";

export const getVacancies = async (
  params?: { [key: string]: string }
): Promise<Array<TableItem>> => {
  const defaultParams: { [key: string]: string } = {
    offset: "0",
    limit: "100",
    query: "",
    area: "",
    salary: "0",
    order_by: "ASC"
  };

  const searchParams = new URLSearchParams();

  Object.keys(defaultParams)
    .forEach((key) => {
      let value = params && params[key];

      searchParams.set(key, String(value && value || defaultParams[key]))
    });

  try {
    const request = await fetch(
      "http://backend:1337/vacancies?" + searchParams.toString()
    );

    const data = await request.json();

    return data.map((item: any) => ({
      id: item.id,
      name: item.name,
      schedule: item.schedule?.name,
      salary: {
        from: item.salary?.from,
        to: item.salary?.to,
        currency: item.salary?.currency
      },
      city: item.area?.name,
      moreUrl: item.alternate_url,
      applyUrl: item.apply_alternate_url
    }));
  } catch (e) {
    console.error(e);
  }

  return [];
};
