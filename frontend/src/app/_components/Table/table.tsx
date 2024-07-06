import React, { FunctionComponent } from "react";

import { Button } from "components/ui/button";
import {
  Table as UITable,
  TableBody,
  TableCell,
  TableHead,
  TableHeader,
  TableRow
} from "components/ui/table";

import { TableProps } from "./types";

export const Table: FunctionComponent<TableProps> = ({ data }) => (
  <UITable>
    <TableHeader>
      <TableRow>
        <TableHead className="w-[100px]">ID</TableHead>
        <TableHead>Название</TableHead>
        <TableHead>График</TableHead>
        <TableHead>Город</TableHead>
        <TableHead>Зарплата</TableHead>
        <TableHead className="text-right">Действия</TableHead>
      </TableRow>
    </TableHeader>
    <TableBody>
      {data.map((
        { id, name, schedule, city, salary, applyUrl, moreUrl }
      ) => (
        <TableRow key={id}>
          <TableCell className="font-medium">{id}</TableCell>
          <TableCell>{name}</TableCell>
          <TableCell>{schedule}</TableCell>
          <TableCell>{city}</TableCell>
          <TableCell>
            от {salary.from} {salary.to && `до ${salary.to}`} {salary.currency}
          </TableCell>
          <TableCell className="text-right flex justify-end gap-4">
            <a href={moreUrl} target="_blank" rel="noreferrer">
              <Button variant="secondary">
                Подробнее
              </Button>
            </a>
            <a href={applyUrl} target="_blank" rel="noreferrer">
              <Button variant="destructive">
                Откликнуться
              </Button>
            </a>
          </TableCell>
        </TableRow>
      ))}
    </TableBody>
  </UITable>
);
